package com.pavan.gamebay.core.domain

import javax.inject.Qualifier


/**
 * Qualifier to mark dependencies that should use the IO dispatcher.
 * Useful for background tasks like database or network operations.
 */

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IODispatcher

/**
 * Qualifier to mark dependencies that should use the Main dispatcher.
 * Useful for UI-related tasks that need to run on the main thread.
 */

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher


/**
 * Qualifier to mark dependencies that should use the Default dispatcher.
 * Useful for CPU-intensive tasks that can benefit from parallel execution.
 * */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher