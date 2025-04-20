package com.dfsek.terra.minestom.chunk;

import com.dfsek.terra.api.util.generic.pair.Pair;

import com.dfsek.terra.api.world.ServerWorld;
import com.dfsek.terra.api.world.biome.generation.BiomeProvider;
import com.dfsek.terra.api.world.chunk.generation.ChunkGenerator;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import net.minestom.server.world.DimensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneratedChunkCache {
    private static final Logger log = LoggerFactory.getLogger(GeneratedChunkCache.class);
    private final LoadingCache<Pair<Integer, Integer>, CachedChunk> cache;
    private final DimensionType dimensionType;
    private final ChunkGenerator generator;
    private final ServerWorld world;
    private final BiomeProvider biomeProvider;

    public GeneratedChunkCache(DimensionType dimensionType, ChunkGenerator generator, ServerWorld world) {
        this.dimensionType = dimensionType;
        this.generator = generator;
        this.world = world;
        this.biomeProvider = world.getBiomeProvider();
        this.cache = Caffeine.newBuilder().maximumSize(128).recordStats().build(
            (Pair<Integer, Integer> key) -> generateChunk(key.getLeft(), key.getRight()));
    }

    private CachedChunk generateChunk(int x, int z) {
        CachedChunk chunk = new CachedChunk(dimensionType.minY(), dimensionType.maxY());
        generator.generateChunkData(chunk, world, biomeProvider, x, z);
        return chunk;
    }

    public void displayStats() {
        CacheStats stats = cache.stats();
        log.info("Avg load time: {}ms | Hit rate: {}% | Load Count: {}", stats.averageLoadPenalty(), stats.hitRate() * 100,
            stats.loadCount());
    }

    public CachedChunk at(int x, int z) {
        return cache.get(Pair.of(x, z));
    }
}
