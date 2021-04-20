package com.orichalcos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Orichalcos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private String id;
    private String name;
}