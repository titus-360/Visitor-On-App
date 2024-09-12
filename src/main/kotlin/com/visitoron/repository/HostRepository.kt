package com.visitoron.repository

import com.visitoron.models.Host
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


/**
 *
 * @author Titus Murithi Bundi
 */
@Repository
interface HostRepository : JpaRepository<Host, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM hosts h WHERE CONCAT(h.name, ' ', h.email, ' ', h.phone, ' ', h.host_department, ' ', h.host_role, ' ', h.company_name) LIKE %:keyword%"
    )
    fun searchHosts(@Param("keyword") keyword: String, pageable: Pageable): Page<Host>
}
