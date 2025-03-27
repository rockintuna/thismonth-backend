package me.rockintuna.thismonthbe.controller;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.service.MemoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
}
