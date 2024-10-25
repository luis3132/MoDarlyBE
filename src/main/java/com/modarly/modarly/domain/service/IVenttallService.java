package com.modarly.modarly.domain.service;

import java.util.List;

import com.modarly.modarly.domain.dto.VenttallDTO;

public interface IVenttallService {
    List<VenttallDTO> saveAll(List<VenttallDTO> venttall);
}
