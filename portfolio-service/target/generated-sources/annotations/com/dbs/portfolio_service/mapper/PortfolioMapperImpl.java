package com.dbs.portfolio_service.mapper;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.entity.Portfolio;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-16T20:53:04+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class PortfolioMapperImpl implements PortfolioMapper {

    @Override
    public Portfolio toEntity(PortfolioRequest request) {
        if ( request == null ) {
            return null;
        }

        Portfolio.PortfolioBuilder portfolio = Portfolio.builder();

        portfolio.customerId( request.getCustomerId() );
        portfolio.portfolioName( request.getPortfolioName() );
        portfolio.portfolioType( request.getPortfolioType() );
        portfolio.investedAmount( request.getInvestedAmount() );
        portfolio.currentValue( request.getCurrentValue() );
        portfolio.profitLoss( request.getProfitLoss() );
        portfolio.riskLevel( request.getRiskLevel() );
        portfolio.status( request.getStatus() );

        return portfolio.build();
    }

    @Override
    public PortfolioResponse toResponse(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioResponse.PortfolioResponseBuilder portfolioResponse = PortfolioResponse.builder();

        portfolioResponse.id( portfolio.getId() );
        portfolioResponse.customerId( portfolio.getCustomerId() );
        portfolioResponse.portfolioName( portfolio.getPortfolioName() );
        portfolioResponse.portfolioType( portfolio.getPortfolioType() );
        portfolioResponse.investedAmount( portfolio.getInvestedAmount() );
        portfolioResponse.currentValue( portfolio.getCurrentValue() );
        portfolioResponse.profitLoss( portfolio.getProfitLoss() );
        portfolioResponse.riskLevel( portfolio.getRiskLevel() );
        portfolioResponse.status( portfolio.getStatus() );
        portfolioResponse.createdAt( portfolio.getCreatedAt() );
        portfolioResponse.updatedAt( portfolio.getUpdatedAt() );

        return portfolioResponse.build();
    }
}
