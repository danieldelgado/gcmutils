package net.jarlehansen.android.gcm.client;

import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: 2/14/13
 * Time: 10:39 AM
 */
@RunWith(RobolectricTestRunner.class)
public class GCMUtilsVerifierTest {
    private PackageInfo packageInfo;
    private ServiceInfo serviceInfo;
    private ServiceInfo[] services;

    @Before
    public void setUp() {
        packageInfo = new PackageInfo();
        serviceInfo = new ServiceInfo();
        services = new ServiceInfo[]{serviceInfo};
    }

    @Test
    public void checkService() {
        serviceInfo.name = GCMIntentService.class.getName();
        packageInfo.services = services;
        GCMUtilsVerifier.checkService(GCMIntentService.class.getPackage().getName(), packageInfo);
    }

    @Test(expected = IllegalStateException.class)
    public void checkService_nullServices() {
        GCMUtilsVerifier.checkService("invalid.package", packageInfo);
    }

    @Test(expected = IllegalStateException.class)
    public void checkService_noServices() {
        packageInfo.services = services;
        GCMUtilsVerifier.checkService("invalid.package", packageInfo);
    }

    @Test(expected = IllegalStateException.class)
    public void checkService_noGCMService() {
        serviceInfo.name = "MyService";
        packageInfo.services = services;
        GCMUtilsVerifier.checkService("invalid.package", packageInfo);
    }

    @Test
    public void checkServiceClass() {
        GCMUtilsVerifier.checkServiceClass(GCMIntentService.class.getName());
    }

    @Test(expected = IllegalStateException.class)
    public void checkServiceClass_invalidType() {
        GCMUtilsVerifier.checkServiceClass(String.class.getName());
    }
}
