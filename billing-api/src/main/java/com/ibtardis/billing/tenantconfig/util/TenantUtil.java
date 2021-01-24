package com.ibtardis.billing.tenantconfig.util;

import java.io.File;
import java.nio.file.Paths;

public class TenantUtil {
    File[] files;
    public TenantUtil(){
        this.files = Paths.get("tenants").toFile().listFiles();
    }

    public File[] getFiles() {
        return files;
    }

}
