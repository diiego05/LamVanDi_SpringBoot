package vn.iotstar.config;

import org.apache.catalina.*;
import org.springframework.util.ResourceUtils;

import java.net.URI;
import java.net.URL;

public class JSPStaticResourceConfigurer implements LifecycleListener {

    private final Context context;
    private final String subPath = "/META-INF";

    public JSPStaticResourceConfigurer(Context context) {
        this.context = context;
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (!event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
            return;
        }

        final URL finalLocation = getUrl();
        this.context.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.RESOURCE_JAR,
                "/",
                finalLocation,
                subPath
        );
    }

    private URL getUrl() {
        final URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();

        if (ResourceUtils.isFileURL(location)) {
            return location; // chạy bằng IDE
        } else if (ResourceUtils.isJarURL(location)) {
            try {
                // Khi chạy fat jar → phải rewrite URL cho JSP
                String locationStr = location.getPath()
                        .replaceFirst("^nested:", "")
                        .replaceFirst("/!BOOT-INF/classes/!/$", "!/");

                return new URI("jar:file", locationStr, null).toURL();
            } catch (Exception e) {
                throw new IllegalStateException("Unable to add new JSP source URI to tomcat resources", e);
            }
        } else {
            throw new IllegalStateException("Can not add tomcat resources, unhandleable url: " + location);
        }
    }
}