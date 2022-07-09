package com.infiniteskills.data

import spock.lang.*

class FirstSpock extends Specification {
    def "hello spock"() {
        given:
        int i = 2

        when:
        i++

        then:
        i == 3
    }
}
