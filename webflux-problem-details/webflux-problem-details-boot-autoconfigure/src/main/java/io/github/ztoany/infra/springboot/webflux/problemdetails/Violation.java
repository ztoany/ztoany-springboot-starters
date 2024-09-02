package io.github.ztoany.infra.springboot.webflux.problemdetails;

public record Violation(String field, String rejectedValue, String message) {
}
