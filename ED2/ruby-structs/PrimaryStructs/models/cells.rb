# frozen_string_literal: true

class SimpleCell
  attr_accessor :successor
  attr_reader :value

  def initialize(value, successor = nil)
    @value = value
    @successor = successor
  end
end
