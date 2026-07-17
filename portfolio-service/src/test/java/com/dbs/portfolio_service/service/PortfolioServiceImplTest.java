package com.dbs.portfolio_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.entity.Portfolio;
import com.dbs.portfolio_service.exception.DuplicatePortfolioException;
import com.dbs.portfolio_service.exception.PortfolioNotFoundException;
import com.dbs.portfolio_service.mapper.PortfolioMapper;
import com.dbs.portfolio_service.repository.PortfolioRepository;
import com.dbs.portfolio_service.service.impl.PortfolioServiceImpl;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private PortfolioMapper portfolioMapper;

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    @Test
    void createPortfolio_success() {

        // Arrange
        PortfolioRequest request = PortfolioRequest.builder()
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        Portfolio portfolio = Portfolio.builder()
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        Portfolio savedPortfolio = Portfolio.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        PortfolioResponse response = PortfolioResponse.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioRepository.existsByPortfolioName("Retirement Fund"))
                .thenReturn(false);

        when(portfolioMapper.toEntity(request))
                .thenReturn(portfolio);

        when(portfolioRepository.save(any(Portfolio.class)))
                .thenReturn(savedPortfolio);

        when(portfolioMapper.toResponse(savedPortfolio))
                .thenReturn(response);

        // Act
        PortfolioResponse result = portfolioService.createPortfolio(request);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Retirement Fund", result.getPortfolioName());

        verify(portfolioRepository, times(1))
                .existsByPortfolioName("Retirement Fund");

        verify(portfolioRepository, times(1))
                .save(any(Portfolio.class));

        verify(portfolioMapper, times(1))
                .toEntity(request);

        verify(portfolioMapper, times(1))
                .toResponse(savedPortfolio);
    }
    
    @Test
    void createPortfolio_duplicatePortfolio() {

        // Arrange
        PortfolioRequest request = PortfolioRequest.builder()
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioRepository.existsByPortfolioName("Retirement Fund"))
                .thenReturn(true);

        // Act & Assert
        DuplicatePortfolioException exception =
                assertThrows(
                        DuplicatePortfolioException.class,
                        () -> portfolioService.createPortfolio(request));

        assertEquals(
                "Portfolio already exists with name : Retirement Fund",
                exception.getMessage());

        verify(portfolioRepository, times(1))
                .existsByPortfolioName("Retirement Fund");

        verify(portfolioRepository, never())
                .save(any());

        verify(portfolioMapper, never())
                .toEntity(any());
    }
    
    @Test
    void getPortfolioById_success() {

        // Arrange
        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        PortfolioResponse response = PortfolioResponse.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioRepository.findById(1L))
                .thenReturn(Optional.of(portfolio));

        when(portfolioMapper.toResponse(portfolio))
                .thenReturn(response);

        // Act
        PortfolioResponse result = portfolioService.getPortfolioById(1L);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getId());

        assertEquals("Retirement Fund", result.getPortfolioName());

        verify(portfolioRepository, times(1))
                .findById(1L);

        verify(portfolioMapper, times(1))
                .toResponse(portfolio);
    }
    
    @Test
    void getPortfolioById_notFound() {

        // Arrange
        when(portfolioRepository.findById(100L))
                .thenReturn(Optional.empty());

        // Act & Assert
        PortfolioNotFoundException exception =
                assertThrows(
                        PortfolioNotFoundException.class,
                        () -> portfolioService.getPortfolioById(100L));

        assertEquals(
                "Portfolio not found with id : 100",
                exception.getMessage());

        verify(portfolioRepository, times(1))
                .findById(100L);

        verify(portfolioMapper, never())
                .toResponse(any());
    }
    
    @Test
    void getAllPortfolios_success() {

        // Arrange
        Portfolio portfolio1 = Portfolio.builder()
                .id(1L)
                .customerId(101L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .build();

        Portfolio portfolio2 = Portfolio.builder()
                .id(2L)
                .customerId(102L)
                .portfolioName("Gold Investment")
                .portfolioType("GOLD")
                .build();

        PortfolioResponse response1 = PortfolioResponse.builder()
                .id(1L)
                .customerId(101L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .build();

        PortfolioResponse response2 = PortfolioResponse.builder()
                .id(2L)
                .customerId(102L)
                .portfolioName("Gold Investment")
                .portfolioType("GOLD")
                .build();

        when(portfolioRepository.findAll())
                .thenReturn(List.of(portfolio1, portfolio2));

        when(portfolioMapper.toResponse(portfolio1))
                .thenReturn(response1);

        when(portfolioMapper.toResponse(portfolio2))
                .thenReturn(response2);

        // Act
        List<PortfolioResponse> result = portfolioService.getAllPortfolios();

        // Assert
        assertNotNull(result);

        assertEquals(2, result.size());
	
        assertEquals("Retirement Fund", result.get(0).getPortfolioName());

        assertEquals("Gold Investment", result.get(1).getPortfolioName());

        verify(portfolioRepository, times(1)).findAll();

        verify(portfolioMapper, times(1)).toResponse(portfolio1);

        verify(portfolioMapper, times(1)).toResponse(portfolio2);
    }
    
    @Test
    void updatePortfolio_success() {

        // Arrange
        Long portfolioId = 1L;

        PortfolioRequest request = PortfolioRequest.builder()
                .customerId(101L)
                .portfolioName("Updated Portfolio")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("100000"))
                .currentValue(new BigDecimal("120000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        Portfolio existingPortfolio = Portfolio.builder()
                .id(portfolioId)
                .customerId(101L)
                .portfolioName("Old Portfolio")
                .portfolioType("EQUITY")
                .build();

        Portfolio updatedPortfolio = Portfolio.builder()
                .id(portfolioId)
                .customerId(101L)
                .portfolioName("Updated Portfolio")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("100000"))
                .currentValue(new BigDecimal("120000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        PortfolioResponse response = PortfolioResponse.builder()
                .id(portfolioId)
                .customerId(101L)
                .portfolioName("Updated Portfolio")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("100000"))
                .currentValue(new BigDecimal("120000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioRepository.findById(portfolioId))
                .thenReturn(Optional.of(existingPortfolio));

        when(portfolioRepository.save(any(Portfolio.class)))
                .thenReturn(updatedPortfolio);

        when(portfolioMapper.toResponse(updatedPortfolio))
                .thenReturn(response);

        // Act
        PortfolioResponse result =
                portfolioService.updatePortfolio(portfolioId, request);

        // Assert
        assertNotNull(result);

        assertEquals(portfolioId, result.getId());

        assertEquals("Updated Portfolio", result.getPortfolioName());

        verify(portfolioRepository).findById(portfolioId);

        verify(portfolioRepository).save(any(Portfolio.class));

        verify(portfolioMapper).toResponse(updatedPortfolio);
    }
    
    @Test
    void deletePortfolio_success() {

        // Arrange
        Long portfolioId = 1L;

        Portfolio portfolio = Portfolio.builder()
                .id(portfolioId)
                .customerId(101L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .build();

        when(portfolioRepository.findById(portfolioId))
                .thenReturn(Optional.of(portfolio));

        doNothing().when(portfolioRepository)
                .delete(portfolio);

        // Act
        portfolioService.deletePortfolio(portfolioId);

        // Assert
        verify(portfolioRepository, times(1))
                .findById(portfolioId);

        verify(portfolioRepository, times(1))
                .delete(portfolio);
    }
}