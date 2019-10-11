require "benchmark/memory"

# First method under test
def allocate_string
  "this string was dynamically allocated"
end

# Second method under test
def give_frozen_string
  "this string is frozen".freeze
end

Benchmark.memory do |x|
  x.report("dynamic allocation") { allocate_string }
  x.report("frozen string") { give_frozen_string }

  x.compare!
end