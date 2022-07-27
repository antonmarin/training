package structure

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ForwardListItemTest {
    @Test
    fun `should return all next forwarded`() {
        val forwardList = ForwardListItem(
            "first",
            ForwardListItem(
                " ",
                ForwardListItem("second")
            )
        )

        assertEquals("first second", forwardList.printAllNext())
    }
}
