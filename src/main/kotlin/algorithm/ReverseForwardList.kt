package algorithm

import structure.ForwardListItem

fun ReverseForwardList(list: ForwardListItem): ForwardListItem {
    if(list.next == null) return list

    var left = list; var current = list.next; var right = current!!.next
    left.next = null

    while (current != null)
    {
        current.next = left
        left = current; current = right; right = right?.next
    }

    return left
}
