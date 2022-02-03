package ru.geracimov.otus.architecture_design_patterns.tanks.ioc

import org.junit.jupiter.api.Test
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.UObjectImpl
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.MovableAdapter
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.RotableAdapter
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RotateCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IoC.Companion.IOC_REGISTER
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IoC.Companion.IOC_SCOPE_CHECKOUT
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IoC.Companion.IOC_UNREGISTER
import kotlin.test.*

internal class IoCTest {
    private val moveCommandKey = "Commands.MoveCommand"
    private val createMoveCommandFunction = { args: Array<Any> ->
        MoveCommand(MovableAdapter(args[0] as UObject))
    }

    private val rotateCommandKey = "Commands.RotateCommand"
    private val createRotateCommandFunction = { args: Array<Any> ->
        RotateCommand(RotableAdapter(args[0] as UObject))
    }

    @Test
    fun `Creating a dependency in the main thread is not available from the other thread and vice versa`() {
        IoC.resolve<Command>(IOC_REGISTER, moveCommandKey, createMoveCommandFunction).execute()
        val moveCommandResolved = IoC.resolve<Command>(moveCommandKey, UObjectImpl())
        assertNotNull(moveCommandResolved)

        val thread = Thread {
            println(Thread.currentThread().name)
            assertFailsWith<IllegalArgumentException>(block = { IoC.resolve<Command>(moveCommandKey, UObjectImpl()) })
            IoC.resolve<Command>(IOC_REGISTER, rotateCommandKey, createRotateCommandFunction).execute()
            val rotateCommandResolved = IoC.resolve<Command>(rotateCommandKey, UObjectImpl())
            assertNotNull(rotateCommandResolved)
        }
        thread.start()
        thread.join()

        assertFailsWith<IllegalArgumentException>(block = { IoC.resolve<Command>(rotateCommandKey, UObjectImpl()) })
    }

    @Test
    fun unregisterDependencyTest() {
        IoC.resolve<Command>(IOC_REGISTER, moveCommandKey, createMoveCommandFunction).execute()
        val moveCommandResolved = IoC.resolve<Command>(moveCommandKey, UObjectImpl())
        assertNotNull(moveCommandResolved)

        IoC.resolve<Command>(IOC_UNREGISTER, moveCommandKey).execute()
        assertFailsWith<IllegalArgumentException>(block = { IoC.resolve<Command>(moveCommandKey, UObjectImpl()) })
    }

    @Test
    fun scopeCheckoutTest() {
        var lastExecutedScopeName = ""
        val someDependencyKey = "Commands.MoveCommand1"

        val funcScope1 = { args: Array<Any> ->
            lastExecutedScopeName = "SCOPE1"
            MoveCommand(MovableAdapter(args[0] as UObject))
        }
        val funcScope2 = { args: Array<Any> ->
            lastExecutedScopeName = "SCOPE2"
            MoveCommand(MovableAdapter(args[0] as UObject))
        }
        IoC.resolve<Command>(IOC_SCOPE_CHECKOUT, "SCOPE1").execute()
        IoC.resolve<Command>(IOC_REGISTER, someDependencyKey, funcScope1).execute()
        IoC.resolve<Command>(IOC_SCOPE_CHECKOUT, "SCOPE2").execute()
        IoC.resolve<Command>(IOC_REGISTER, someDependencyKey, funcScope2).execute()
        assertEquals("", lastExecutedScopeName)

        IoC.resolve<Command>(IOC_SCOPE_CHECKOUT, "SCOPE1").execute()
        val resolvedCommand1 = IoC.resolve<Command>(someDependencyKey, UObjectImpl())
        assertEquals("SCOPE1", lastExecutedScopeName)
        assertNotNull(resolvedCommand1)

        IoC.resolve<Command>(IOC_SCOPE_CHECKOUT, "SCOPE2").execute()
        val resolvedCommand2 =    IoC.resolve<Command>(someDependencyKey, UObjectImpl())
        assertEquals("SCOPE2", lastExecutedScopeName)
        assertNotNull(resolvedCommand2)
    }

    @Test
    fun iocCreatesCommandOnlyWhenDependencyIsResolvedText() {
        var isExecuted = false

        val func = { args: Array<Any> ->
            isExecuted = true
            MoveCommand(MovableAdapter(args[0] as UObject))
        }
        IoC.resolve<Command>(IOC_REGISTER, "Commands.MoveCommand2", func).execute()
        assertFalse(isExecuted)
        IoC.resolve<Command>("Commands.MoveCommand2", UObjectImpl())
        assertTrue(isExecuted)
    }

}