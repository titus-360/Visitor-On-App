package com.visitoron.config.audit

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

/**
 *
 * @author Titus Murithi Bundi
 */
@Configuration
@EnableJpaAuditing
class AuditConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware<String> {
            Optional.of("Titus Bundi")
        }
    }
}
