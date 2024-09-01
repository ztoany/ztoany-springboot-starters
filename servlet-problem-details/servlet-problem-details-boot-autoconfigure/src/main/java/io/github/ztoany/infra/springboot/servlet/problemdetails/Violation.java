package io.github.ztoany.infra.springboot.servlet.problemdetails;

public record Violation(String field, String rejectedValue, String message) {
}
