package com.petke.budgeting.service;

import com.petke.budgeting.exception.InvalidRequestException;
import com.petke.budgeting.exception.RegistryNotFoundException;
import com.petke.budgeting.model.Registry;
import com.petke.budgeting.model.request.RechargeRequest;
import com.petke.budgeting.model.request.TransferRequest;
import com.petke.budgeting.model.response.RegistryResponse;
import com.petke.budgeting.repository.RegistryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RegistryService {

    private final RegistryRepository registryRepository;

    public RegistryService(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public List<RegistryResponse> findByUserId(String userId) {
        List<RegistryResponse> registryResponse = registryRepository
                .findByUserId(userId)
                .stream()
                .map(RegistryResponse::new)
                .collect(Collectors.toUnmodifiableList());

        if (registryResponse.isEmpty()) {
            throw new RegistryNotFoundException(String.format("No registries found for userId: '%s'", userId));
        }

        return registryResponse;
    }

    @Transactional
    public void recharge(String userId, String registryId, RechargeRequest rechargeRequest) {
        Registry registry = registryRepository.findByIdAndUserId(registryId, userId);

        if (Objects.isNull(registry)) {
            throw new RegistryNotFoundException(String.format("Registry '%s' not found for user: '%s'", registryId, userId));
        }

        registry.setAmount(registry.getAmount().add(rechargeRequest.getAmount()));
    }

    @Transactional
    public void transfer(String userId, String registryId, TransferRequest transferRequest) {
        Registry sourceRegistry = registryRepository.findByIdAndUserId(registryId, userId);
        Registry targetRegistry = registryRepository.findByIdAndUserId(transferRequest.getTargetRegistryId(), userId);

        if (Objects.isNull(sourceRegistry)) {
            throw new RegistryNotFoundException(String.format("Source registry '%s' not found for user: '%s'", registryId, userId));
        }

        if (Objects.isNull(targetRegistry)) {
            throw new RegistryNotFoundException(String.format("Target registry '%s' not found for user: '%s'",
                    transferRequest.getTargetRegistryId(), userId));
        }

        if (sourceRegistry.getAmount().floatValue() < transferRequest.getAmount().floatValue()) {
            throw new InvalidRequestException(String.format("Not enough funds for the transfer. Source amount: %s, requested transfer: %s",
                    sourceRegistry.getAmount(), transferRequest.getAmount()));
        }

        sourceRegistry.setAmount(sourceRegistry.getAmount().subtract(transferRequest.getAmount()));
        targetRegistry.setAmount(targetRegistry.getAmount().add(transferRequest.getAmount()));
    }
}
