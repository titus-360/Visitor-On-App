package com.visitoron.controllers

import com.visitoron.dtos.VisitorDto
import com.visitoron.models.Host
import com.visitoron.service.VisitorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Titus Murithi Bundi
 */
@RestController
@RequestMapping("/api/v1/visitors")
class VisitorController(private val visitorService: VisitorService) {

    @PostMapping("/create-new-visitor")
    fun createNewVisitor(@RequestBody visitorDto: VisitorDto): ResponseEntity<VisitorDto> {
        val newVisitor = visitorService.createVisitor(visitorDto)
        return ResponseEntity.ok(newVisitor)
    }

    @PostMapping("/select-host/{visitorId}/{hostId}")
    fun selectHost(@PathVariable visitorId: Long, @PathVariable hostId: Long): ResponseEntity<Host> {
        val selectedHost = visitorService.selectHostPerson(visitorId, hostId)
        return ResponseEntity.ok(selectedHost)
    }
}
