package projects.proj03;

class Queens {
	private int row[];
	private int col[];
	private int n;
	private int total;
	private boolean displayResults = true;
	
	private Queens() {
	}
	
	Queens(int n) {
		if (n > 0) {
			this.n = n;
			this.row = new int[n];
			this.col = new int[n];
			queens(-1);
		}
		showTotal();
	}
	
	Queens(int n, boolean displayResults) {
		if (n > 0) {
			this.n = n;
			this.row = new int[n];
			this.col = new int[n];
			this.displayResults = displayResults;
			queens(-1);
		}
		if (displayResults)
			showTotal();
	}
	
	int getTotal() {
		return this.total;
	}
	
	void showTotal() {
		System.out.println("N = " + n + " -> Total: " + total + (total > 1 ? " results" : " result"));
	}
	
	private void queens(int i) {
		if (promising(i)) {
			if (i == n - 1) {
				if (displayResults) {
					System.out.println("Result " + (++total) + ": ");
					for (int j = 0; j < n; j++) {
						System.out.println(row[j] + "\t" + col[j] + "\t" + j);
					}
				} else total++;
			} else {
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						row[i + 1] = r;
						col[i + 1] = c;
						queens(i + 1);
					}
				}
			}
		}
	}
	
	private boolean promising(int i) {
		boolean sw = true;
		int k = 0;
		while (k < i && sw) {
			if (row[i] == row[k] || col[i] == col[k] ||
					(Math.abs(row[i] - row[k]) == i - k && Math.abs(col[i] - col[k]) == i - k))
				sw = false;
			k++;
		}
		return sw;
	}
}
