package org.example

class Account /* TODO */

class Bank {
    fun transfer(from: Account, to: Account, amount: /* TODO */ Any) {
        TODO("where's my money lebowski?!")
    }
}

fun main() {
    val alice = Account()
    val bob = Account()
    val amount = Any()

    val bank = Bank()

    log("send $amount from alice[$alice] to bob[$bob]")
    bank.transfer(alice, bob, amount)
    log("result accounts: alice[$alice], bob[$bob]")
}