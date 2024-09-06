package com.visitoron.repository

import com.visitoron.models.Visitor
import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 * @author Titus Murithi Bundi
 */
interface VisitorRepository : JpaRepository<Visitor, Long> {
}
