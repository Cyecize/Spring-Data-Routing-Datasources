package com.cyecize.demo.config.db;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataSourceType {

    PRIMARY("primary"), SECONDARY("secondary"), TERTIARY("tertiary");

    private final String name;
}
