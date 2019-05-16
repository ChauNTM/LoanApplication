# LoanApplication
* Guideline to build and run application:
- Clone or download application, just open it by Android studio and run it on emulator or real device. Application doesn't require any extra steps to build and run.

* Guideline to run unit test:
- Unit test contains 2 parts attach with 2 modules: app and data
+ Module App: HomeViewModelTest and RegisterViewModelTest, both are used to test the logic inside view model. Run both.
+ Module data: with every models, we have to test logic inside repository attaches with that model and I added extra tests to verify parse data from json. So, we have to run 4 test files: OfferRepositoryMappingTest, OfferRepositoryTest, UserInfoMappingTest, UserRepositoryTest.

- Note: I don't have enough time to write unit test for fragments and ui test. So, I'll update later as soon as posible.
