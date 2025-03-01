package com.pavan.gamebay.core.domain

/**
 * A functional interface for mapping an input of type I to an output of type O.
 *
 * @param I the input type
 * @param O the output type
 */
fun interface IMapper<in I, out O> {
    /**
     * Maps the given input to an output.
     *
     * @param input the input value
     * @return the mapped output value
     */
    fun map(input: I): O
}