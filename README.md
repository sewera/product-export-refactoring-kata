# Product export refactoring kata

Check out the [resources](https://www.sewera.dev/rtp) for this kata.

The main book for this kata is [Joshua Kerievsky's "Refactoring to Patterns"](https://www.google.com/books/edition/Refactoring_to_Patterns/Oxm8R1dY3RwC).

This kata is based on Emily Bache's version:
[emilybache/Product-Export-Refactoring-Kata](https://github.com/emilybache/Product-Export-Refactoring-Kata).

## Tags

1. [t-initial-setup]
   - This is what we'd find in a legacy code
   - No tests
2. [t-tests-in-place]
   - System is under test
3. [t-test-for-different-tax-calculation]
   - Code coverage tool uncovered a different tax calculation algorithm
4. [t-first-test-refactoring]
   - Test code should also be refactored
5. [t-add-lombok-dependency]
   - We are Java developers of the modern age
   - Some people will call it poor man's Kotlin. And they'd be right
6. [t-classes-lombokified-and-simplified]
   - Look at this removed code, oh my, the [removed code](https://github.com/sewera/product-export-refactoring-kata/compare/t-first-test-refactoring...t-classes-lombokified-and-simplified)!
7. [t-tax-calculator-under-test]
   - Tax calculation is tricky and expensive to break
   - We have to make sure we didn't break anything
8. [t-ledger-introduced]
   - We finally got our domain expert
   - How would you call something that holds a list of orders?
9. [t-ledger-renamed-to-account]
   - After further discussions,
     our domain expert says that it should be an Account.
10. [t-tax-calculator-removed]
    - But tax calculation is hard, we don't want to lose it!
    - Don't worry, now the Account is responsible for it
11. [t-rtp-price-implicit-leaf-extracted]
    - Alright, _finally_ we can do the refactoring to a Composite pattern
    - Look at [one of the commits](https://github.com/sewera/product-export-refactoring-kata/commit/57809d0383f7ee0b4184ded14e138f73bedf95c5)
      - It's very small
      - There are a lot of such commits, so Pair Programming may be necessary
12. [t-strategy-pattern-in-product]
    - [Introduction](https://github.com/sewera/product-export-refactoring-kata/commit/11b9948368649ef1df9644aa50c13779b2e48737)
    - [Implementation in StoreEvent](https://github.com/sewera/product-export-refactoring-kata/commit/802b2148a7863c4b4fea237007c80e9d0a4d41b1#diff-2743d87c473294f182a077056669ad94c433d76ba30b63452dcc32f27d6eef23R33-R41)
    - That strategy pattern came out of nowhere!
      - But it solved a problem of duplication at that time
      - It turned out not to be useful later, so it was removed
13. [t-rtp-refactoring-to-composite-done]
    - What a journey that was
    - The design is better, the tests pass after each commit,
      so we could continuously deliver it to production
    - Better, shorter functions
    - But still not declarative enough
14. [t-domain-objects-moved-to-domain-package]
    - Wait, why are there xml representations of domain objects
      in the objects themselves?
    - You can argue that it _is_ the domain of the project,
      because the project is solely for exporting XML
15. [t-rtp-xml-builder-implemented]
    - Luckily, we've got some spare time,
      so we'll make the design _even better_
      by implementing the Builder pattern
16. [t-rtp-encapsulated-composite-with-builder-in-price]
    - Now the design better reflects the intent
    - It's more declarative instead of imperative
17. [t-rtp-simplify-price-after-encapsulation-with-builder]
    - Further simplification
18. [t-rtp-simplify-serialization-in-xml-exporter]
    - Further deduplication
19. [t-refactored]
    - That's much better
    - Thankfully we did it in Pair Programming,
      because a [PR for it](https://github.com/sewera/product-export-refactoring-kata/compare/t-initial-setup...t-refactored) would be huge!

[t-initial-setup]: https://github.com/sewera/product-export-refactoring-kata/tree/t-initial-setup
[t-tests-in-place]: https://github.com/sewera/product-export-refactoring-kata/tree/t-tests-in-place
[t-test-for-different-tax-calculation]: https://github.com/sewera/product-export-refactoring-kata/tree/t-test-for-different-tax-calculation
[t-first-test-refactoring]: https://github.com/sewera/product-export-refactoring-kata/tree/t-first-test-refactoring
[t-add-lombok-dependency]: https://github.com/sewera/product-export-refactoring-kata/tree/t-add-lombok-dependency
[t-classes-lombokified-and-simplified]: https://github.com/sewera/product-export-refactoring-kata/tree/t-classes-lombokified-and-simplified
[t-tax-calculator-under-test]: https://github.com/sewera/product-export-refactoring-kata/tree/t-tax-calculator-under-test
[t-ledger-introduced]: https://github.com/sewera/product-export-refactoring-kata/tree/t-ledger-introduced
[t-ledger-renamed-to-account]: https://github.com/sewera/product-export-refactoring-kata/tree/t-ledger-renamed-to-account
[t-tax-calculator-removed]: https://github.com/sewera/product-export-refactoring-kata/tree/t-tax-calculator-removed
[t-rtp-price-implicit-leaf-extracted]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-price-implicit-leaf-extracted
[t-strategy-pattern-in-product]: https://github.com/sewera/product-export-refactoring-kata/tree/t-strategy-pattern-in-product
[t-rtp-refactoring-to-composite-done]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-refactoring-to-composite-done
[t-domain-objects-moved-to-domain-package]: https://github.com/sewera/product-export-refactoring-kata/tree/t-domain-objects-moved-to-domain-package
[t-rtp-xml-builder-implemented]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-xml-builder-implemented
[t-rtp-encapsulated-composite-with-builder-in-price]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-encapsulated-composite-with-builder-in-price
[t-rtp-simplify-price-after-encapsulation-with-builder]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-simplify-price-after-encapsulation-with-builder
[t-rtp-simplify-serialization-in-xml-exporter]: https://github.com/sewera/product-export-refactoring-kata/tree/t-rtp-simplify-serialization-in-xml-exporter
[t-refactored]: https://github.com/sewera/product-export-refactoring-kata/tree/t-refactored
