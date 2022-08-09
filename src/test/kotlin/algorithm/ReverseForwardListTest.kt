package algorithm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import structure.ForwardListItem

internal class ReverseForwardListTest {
    @Test
    fun `should reverse input`() {
        val forwardList = ForwardListItem(
            "first",
            ForwardListItem(
                " ",
                ForwardListItem(
                    "second",
                    ForwardListItem(
                        " ",
                        ForwardListItem("third")
                    )
                )
            )
        )

        val result = ReverseForwardList(forwardList)

        assertEquals("third second first", result.printAllNext())
    }
}
