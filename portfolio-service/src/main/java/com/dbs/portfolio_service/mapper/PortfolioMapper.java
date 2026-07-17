package com.dbs.portfolio_service.mapper;

import org.mapstruct.Mapper;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.entity.Portfolio;

@Mapper(componentModel = "spring")
public interface PortfolioMapper 
{

    Portfolio toEntity(PortfolioRequest request);

    PortfolioResponse toResponse(Portfolio portfolio);

}
