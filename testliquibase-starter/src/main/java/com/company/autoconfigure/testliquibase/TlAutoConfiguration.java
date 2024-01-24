package com.company.autoconfigure.testliquibase;

import com.company.testliquibase.TlConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({TlConfiguration.class})
public class TlAutoConfiguration {
}

