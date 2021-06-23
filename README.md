# Amazing Numbers

A program that indicates various properties of numbers for a single number or range of numbers.

Properties include:

- even
- odd
- buzz
- duck
- palindromic
- gapful
- spy
- sunny
- square
- jumping
- sad
- happy

If a user enters one number, the properties for that particular number are printed. 
```
Enter a request:
10

Properties of 10
        even: true
        odd: false
        buzz: false
        duck: true
 palindromic: false
      gapful: false
         spy: false
      square: false
       sunny: false
     jumping: true
       happy: true
         sad: false
```
If a user enters two numbers (e.g. 1 10), the properties for each number from 1 through 10 will show:
```
Enter a request:
1 10

1 is odd, palindromic, spy, square, jumping, happy
2 is even, palindromic, spy, jumping, sad
3 is odd, palindromic, spy, sunny, jumping, sad
4 is even, palindromic, spy, square, jumping, sad
5 is odd, palindromic, spy, jumping, sad
6 is even, palindromic, spy, jumping, sad
7 is odd, buzz, palindromic, spy, jumping, happy
8 is even, palindromic, spy, sunny, jumping, sad
9 is odd, palindromic, spy, square, jumping, sad
10 is even, duck, jumping, happy
```
A user can specify `n` number of properties to include or exlude (-).
```
Enter a request:
1 5 -odd

2 is even, palindromic, spy, jumping, sad
4 is even, palindromic, spy, square, jumping, sad
6 is even, palindromic, spy, jumping, sad
8 is even, palindromic, spy, sunny, jumping, sad
10 is even, duck, jumping, happy
```

```
Enter a request:
1 5 odd

1 is odd, palindromic, spy, square, jumping, happy
3 is odd, palindromic, spy, sunny, jumping, sad
5 is odd, palindromic, spy, jumping, sad
7 is odd, buzz, palindromic, spy, jumping, happy
9 is odd, palindromic, spy, square, jumping, sad
```

```
Enter a request:
1 5 even sunny happy -duck -gapful

3968 is even, sunny, happy
34224 is even, sunny, happy
75624 is even, sunny, happy
134688 is even, sunny, happy
178928 is even, sunny, happy
```

![Kapture 2021-06-22 at 20 24 07](https://user-images.githubusercontent.com/64165327/123017116-f5aaca80-d399-11eb-83c7-42b2193d8cde.gif)


