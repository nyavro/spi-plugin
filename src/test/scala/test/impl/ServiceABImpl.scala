package test.impl

import test.spi.{ServiceBSpi, ServiceASpi}

class ServiceABImpl extends ServiceASpi with ServiceBSpi {
}
