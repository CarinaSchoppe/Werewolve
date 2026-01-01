package me.carinaschoppe.werewolf.core.persistence


import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.nio.file.Path
import java.sql.Connection

/**
 * Centralised bootstrap responsible for connecting Exposed to the SQLite database.
 */
object DatabaseConfig {
    @Volatile
    private var initialized: Boolean = false

    /** Establishes the connection (idempotently) and ensures schemas exist. */
    fun connect(databasePath: Path) {
        if (!initialized) {
            synchronized(this) {
                if (!initialized) {
                    Database.connect(url = "jdbc:sqlite:${databasePath.toAbsolutePath()}", driver = "org.sqlite.JDBC")
                    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
                    transaction {
                        TODO("Create tables")
                    }
                    initialized = true
                }
            }
        }
    }
}
