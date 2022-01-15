package net.onvoid.autosmelt;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.onvoid.autosmelt.proxy.ClientProxy;
import net.onvoid.autosmelt.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("autosmelt")
public class AutoSmelt
{
    public static final String MODID = "autosmelt";
    public static AutoSmelt instance;
    public static CommonProxy proxy;
    private static final Logger LOGGER = LogManager.getLogger();

    public AutoSmelt() {
        instance = this;

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.start();
    }
}
