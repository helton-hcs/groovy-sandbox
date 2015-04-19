package simplisp.parser

import simplisp.types.*

class SimplispParser {
	private environment = [:]

	def List tokenize(str) {
		str.replace('(', ' ( ')
		   .replace(')', ' ) ')
		   .split()
	}
 
	def parse(program) {
		readFromTokens(tokenize(program))
	}

	def private atom(token) {
		if (token.toString().isInteger()) {
			token.toInteger()
		}
		else if (token.toString().isBigDecimal()) {
			token.toBigDecimal()
		}
		else {
			token.toString()
		}
	}

	def private readFromTokens(tokens) {
		if (tokens.isEmpty()) {
			throw new SyntaxError("Unexpected EOF while reading.")
		}
		def token = tokens.first()
		tokens.remove(0)
		switch (token) {
			case '(':
				def list = []
				while (tokens.first() != ')') {
					list << readFromTokens(tokens)
				}
				tokens.remove(0)
				return list
				break
			case ')':
				throw new SyntaxError("Unexpected ')'")
			case '#f':
				return false
			case '#t':
				return true
			default:
				return atom(token)
		}
	}

}	
