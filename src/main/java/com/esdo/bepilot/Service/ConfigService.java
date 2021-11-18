package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Request.ConfigRequest;
import com.esdo.bepilot.Model.Response.ConfigResponse;

public interface ConfigService {
    ConfigResponse getConfig();
    ConfigResponse editConfig(ConfigRequest request);
}
