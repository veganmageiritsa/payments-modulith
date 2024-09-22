@org.springframework.modulith.ApplicationModule(
    allowedDependencies = { "inventory::port.in", "inventory::port.domain", "exception" , "eventaction::action", "orders::domain.events" }
)
package com.nl.paymentsmodulith.orders;