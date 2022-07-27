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
// разворачиваем односвязный список за O(n)
//func (chain *links) Reverse() *links {
//    if chain == nil || chain.Next == nil {
//        return chain
//    }
//
//    left, next := chain, chain.Next.Next
//    chain = chain.Next
//    left.Next = nil
//
//    for {
//        chain.Next = left
//
//        if next == nil {
//            break
//        }
//
//        left, chain, next = chain, next, next.Next
//    }
//
//    return chain
//}

// Создаём список из чего-нибудь, умеющего выдавать по одной строке за раз
//func CreateFromReader(readline func() *string) *links {
//    var chain, start *links = nil, nil
//
//    for {
//        if line := readline(); line != nil {
//            link := &links{ nil, *line }
//
//            if chain == nil {
//                chain, start = link, link
//            } else {
//                chain.Next, chain = link, link
//            }
//
//        } else {
//            return start
//        }
//    }
//
//    return nil
//}

// Точка входа
//func main() {
//    if len(os.Args) < 2 {
//        os.Exit(1)
//    }
//
//    f, err := os.OpenFile(os.Args[1], os.O_RDONLY, 0666)
//
//    if err != nil {
//        os.Exit(2)
//    }
//
//    defer f.Close()
//
//    r := bufio.NewReader(f)
//
//    CreateFromReader(
//        func() (*string) {
//            if line, e := r.ReadString('\n'); e == nil {
//            return &line
//        }
//
//            return nil
//        },
//    ).Reverse().Print()
//}
