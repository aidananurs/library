package com.netcracker.library.controller;

import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.model.MemberDto;
import com.netcracker.library.model.SaveMemberRequest;
import com.netcracker.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        try {
            var members = memberService.getAll();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
        Optional<MemberDto> member = memberService.getById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody SaveMemberRequest request) {
        try {
            memberService.save(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody SaveMemberRequest request) {
        try {
            memberService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    private ResponseEntity<?> createInternalServerErrorStatus(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + message);
    }
}
