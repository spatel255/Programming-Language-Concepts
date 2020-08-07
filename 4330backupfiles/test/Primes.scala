def isPrime(n: Int): Boolean =
  (2 until (n/2) + 1) forall (d => n%d != 0)

def crossOut(m: Int, ns: List[Int]): List[Int] =
  l=[Int]
  for i in range(len(ns)):
  if ns[i]%m!=0:
  l.append(ns[i])
  return l

def sieve(ns: List[Int]): List[Int] =
  n=max(ns)
  prime = [True for i in range(n+1)]
  p = 2
  while (p * p <= n):
  if (prime[p] == True):
  for i in range(p * p, n+1, p):
  prime[i] = False
  p += 1
  primes=[]
  //Print prime numbers
  for p in range(2, n+1):
  if prime[p]:
  primes.append(p)
  return primes

def isPrime(n):
  if n <= 1:
  return False
  if n == 2:
  return True
  if n%2 == 0:
  return False
  for i in range(3, int(math.sqrt(n))+1, 2):
  if n%i == 0:
  return False
  return True

def isSumOfTwoPrimes(n: Int): Boolean =
  if isPrime(n) and isPrime(n - 2):
  return True
  else:
  return False

def isPrime(n):
  if n <= 1:
  return False
  if n == 2:
  return True
  if n%2 == 0:
  return False
  for i in range(3, int(math.sqrt(n))+1, 2):
  if n%i == 0:
  return False
  return True

def isSumOfTwoPrimes(n):
  if isPrime(n) and isPrime(n - 2):
  return True
  else:
  return False

def even4To100SumOfPrimes =
  for i in range(4,101):
  if not isSumOfTwoPrimes(i):
  return False
  return True
