package ru.clevertec.videohostingchannels.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.service.ChannelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelResponse> saveByAuthorId(@RequestParam Long authorId,
                                                          @RequestPart ChannelRequest request,
                                                          @RequestPart MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.saveByAuthorId(authorId, request, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponse> updateById(@PathVariable Long id,
                                                      @RequestPart ChannelRequest request,
                                                      @RequestPart MultipartFile file) {
        return ResponseEntity.ok(channelService.updateById(id, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ChannelFilterResponse>> findAllByFilter(@RequestParam(required = false) String name,
                                                                       @RequestParam(required = false) String language,
                                                                       @RequestParam(required = false) String category,
                                                                       Pageable pageable) {
        return ResponseEntity.ok(channelService.findAllByFilter(name, language, category, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelDetailedInformationResponse> findDetailedInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(channelService.findDetailedInformationById(id));
    }

}
