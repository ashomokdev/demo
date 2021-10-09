package com.comeon.demo.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@JsonSerializableSchema
@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String email;
    private int count;
}