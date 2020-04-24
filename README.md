# Tip Calculator 

## *Your name here*

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip.

Time spent: **8** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [X] User can enter in a bill amount (total amount to tip on)
* [X] User can enter a tip percentage (what % the user wants to tip).
* [X] The tip and total amount are updated immediately when any of the inputs changes.
* [X] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [X] Add color to tip description
* [X] Add emooji to tip description
* [X] Add the ability to split the bill across any number of people.
* [X] Allow the user to change their currency.
* [X] Add another screen to the app where you can see tips made in the past. (Only available for the current session)

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://imgur.com/Tu9nMjQ' title='Video Walkthrough' width='' alt='Video Walkthrough' />
Video link: https://imgur.com/Tu9nMjQ

## Notes

- Learned to use a RecycleView to show tip history
- Used a global variable to hold the tip history
    - Don't want to pass by intent, because the data would be lost upon returning to the previous screen
    
## License

    Copyright 2020 Qiwen Wang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
