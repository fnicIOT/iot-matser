package com.fnic.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface HaoThinkService {

    @Transactional
    public Map<String,Object> addDevice(Map<String, Object> param);

}
