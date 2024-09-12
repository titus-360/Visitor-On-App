package com.visitoron.controllers

import com.visitoron.models.Host
import com.visitoron.service.HostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Titus Murithi Bundi
 */
@RestController
@RequestMapping("/api/v1/hosts")
class HostController(private val hostService: HostService) {

    @PostMapping("/create-new-host")
    fun createNewHost(@RequestBody host: Host): ResponseEntity<Host> {
        val newHost = hostService.createHost(host)
        return ResponseEntity.ok(newHost)
    }

    @PostMapping("/get-all-hosts")
    fun getAllHosts(pageable: Pageable): ResponseEntity<Page<Host>> {
        val allHosts = hostService.getAllHosts(pageable)
        return ResponseEntity.ok(allHosts)
    }

    @PostMapping("/search-hosts")
    fun searchHosts(@RequestBody keyword: String, pageable: Pageable): ResponseEntity<Page<Host>> {
        val searchedHosts = hostService.searchHosts(keyword, pageable)
        return ResponseEntity.ok(searchedHosts)
    }

    @GetMapping("/get-host-by-id/{hostId}")
    fun selectHost(@PathVariable hostId: Long): ResponseEntity<Host> {
        val host = hostService.getHostById(hostId)
        return ResponseEntity.ok(host)
    }
}
