package me.xsiet.eventflag.plugin.loader;

import com.google.gson.Gson;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class EventFlagPluginLoader implements PluginLoader {
    private record PluginLibraries(Map<String, String> repositories, List<String> dependencies) {
        public Stream<RemoteRepository> asRepositories() {
            Stream<Map.Entry<String, String>> stream = repositories.entrySet().stream();
            return stream.map(it -> new RemoteRepository.Builder(
                    it.getKey(),
                    "default",
                    it.getValue()
            ).build());
        }
        public Stream<Dependency> asDependencies() {
            Stream<String> stream = dependencies.stream();
            return stream.map(it -> new Dependency(new DefaultArtifact(it), null));
        }
    }
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        PluginLibraries pluginLibraries;
        try (InputStream inputStream = getClass().getResourceAsStream("/paper-libraries.json")) {
            assert inputStream != null;
            pluginLibraries = new Gson().fromJson(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8),
                    PluginLibraries.class
            );
        }
        catch (IOException ioException) { throw new RuntimeException(ioException); }
        pluginLibraries.asRepositories().forEach(resolver::addRepository);
        pluginLibraries.asDependencies().forEach(resolver::addDependency);
        classpathBuilder.addLibrary(resolver);
    }
}