package io.github.ztoany.infra.springboot.problemdetails;

public record Violation(String field, String rejectedValue, String message) {
}
