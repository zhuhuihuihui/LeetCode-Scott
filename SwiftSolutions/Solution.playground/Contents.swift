//: Playground - noun: a place where people can play

import UIKit

class Solution {
    
    /**
     * 125. Valid Palindrome
     * String, Easy
     * https://leetcode.com/problems/valid-palindrome/
     * */
    func isPalindrome(s: String) -> Bool {
        let alphanumericCharSet = NSCharacterSet.alphanumericCharacterSet()
        if !s.isEmpty {
            var lefCursor = s.startIndex, rightCursor = s.endIndex.predecessor()
            
            while lefCursor < rightCursor {
                let leftChar = s[lefCursor], rightChar = s[rightCursor]
                print("leftChar = \(leftChar), rightChar = \(rightChar)")
                if alphanumericCharSet.characterIsMember(leftChar as! unichar) && alphanumericCharSet.characterIsMember(rightChar) {
                    if leftChar != rightChar {
                        return false
                    }
                }
                
                if !alphanumericCharSet.characterIsMember(leftChar) {
                    lefCursor = lefCursor.successor()
                }
                
                if !alphanumericCharSet.characterIsMember(rightChar) {
                    rightCursor = rightCursor.predecessor()
                }
            }
            return true
        }
        return false
    }
    
    func description(string: String) {
        print(string)
    }
}

let solution = Solution()
let result = solution.isPalindrome("A man, a plan, a canal: Panama")
