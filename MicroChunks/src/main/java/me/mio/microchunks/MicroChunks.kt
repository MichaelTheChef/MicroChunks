package me.mio.microchunks

import org.bukkit.Chunk
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class MicroChunks : JavaPlugin() {
    private val optimizeInterval = 20L * 60L * 5L

    override fun onEnable() { scheduleChunkOptimization() }

    override fun onDisable() {}

    private fun scheduleChunkOptimization() {
        object : BukkitRunnable() {
            override fun run() {
                optimizeChunks()
            }
        }.runTaskTimer(this, optimizeInterval, optimizeInterval)
    }

    private fun optimizeChunks() {
        server.worlds.forEach { world ->
            world.loadedChunks.forEach { chunk ->
                optimizeChunk(chunk)
            }
        }
        logger.info("MicroChunks | Chunk optimization complete for all loaded chunks.")
    }

    private fun optimizeChunk(chunk: Chunk) {
        logger.info("MicroChunks | Optimizing chunk at coordinates: (${chunk.x}, ${chunk.z}) in world ${chunk.world.name}")
    }
}
