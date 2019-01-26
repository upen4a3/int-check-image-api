package com.d3banking.training.controller;

import com.d3banking.core.sdk.account.service.CoreUserAccountService;
import com.d3banking.sdk.Actor;
import com.d3banking.sdk.context.D3RequestContext;
import com.d3banking.sdk.context.D3RequestContextHolder;
import com.d3banking.util.MapBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.d3banking.training.service.CheckImageDTO;
import com.d3banking.training.service.CheckImageService;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("v4/accounts/{userAccountId}/transactions/{transactionId}/images")
public class CheckImageController {

    private final CoreUserAccountService coreUserAccountService;

    private final CheckImageService checkImageService;


    public CheckImageController(CoreUserAccountService coreUserAccountService, CheckImageService checkImageService)
    {
        this.coreUserAccountService = coreUserAccountService;
        this.checkImageService = checkImageService;
    }

    @GetMapping
    public List<CheckImageDTO>listImages(@PathVariable Long userAccountId, @PathVariable Long transactionId) {
        D3RequestContext requestContext = D3RequestContextHolder.get();
        Actor actor = ((D3RequestContext) requestContext).getActor();
        Long userId = actor.getActingAsId();
        return
                coreUserAccountService.findSummaryByUserIdAndUserAccountId(userId, userAccountId)
                .map(userAccount -> checkImageService.getImage(transactionId))
                .orElse(Collections.EMPTY_LIST);

    }
    @GetMapping("debug")
    public Map<String,Object>debug(@PathVariable Long userAccountId,@PathVariable Long transactionId)
    {
        D3RequestContext requestContext =  D3RequestContextHolder.get();
        Actor actor = ((D3RequestContext) requestContext).getActor();

        return new MapBuilder<String,Object>()
                .put("userAccountId",userAccountId)
                .put("transactionId",transactionId)
                .put("userAccount",coreUserAccountService.findByUserIdAndUserAccountId(actor.getActingAsId(),userAccountId))
                .put("d3RequestContext", D3RequestContextHolder.get()).build();
    }


}