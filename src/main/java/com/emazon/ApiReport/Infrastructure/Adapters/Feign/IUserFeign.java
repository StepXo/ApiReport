package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Application.Response.UserResponse;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = InfraConstants.USER_API, url = "${user.api.url}",configuration = FeignConfiguration.class)
public interface IUserFeign {

    @GetMapping(value = InfraConstants.USER,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    UserResponse getUserById(@PathVariable String id);

}
