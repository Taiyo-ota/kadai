document.addEventListener("DOMContentLoaded", () => {

	const monthLabel = document.getElementById("monthLabel");
	const calendarGrid = document.getElementById("calendarGrid");

	const prevButton = document.getElementById("prevMonth");
	const nextButton = document.getElementById("nextMonth");
	const filterSelect = document.getElementById("genreFilter");

	let currentDate = new Date();

	// カレンダーを表示
	function updateMonth() {

		const year = currentDate.getFullYear();
		const month = currentDate.getMonth();

		if (monthLabel) {
			monthLabel.textContent = `${year}年${month + 1}月`;
		}

		if (!calendarGrid) {
			return;
		}

		// 曜日の見出しを残す
		calendarGrid.innerHTML = `
            <div class="calendar-day heading">日</div>
            <div class="calendar-day heading">月</div>
            <div class="calendar-day heading">火</div>
            <div class="calendar-day heading">水</div>
            <div class="calendar-day heading">木</div>
            <div class="calendar-day heading">金</div>
            <div class="calendar-day heading">土</div>
        `;

		// 月の最初の曜日
		const firstDay = new Date(year, month, 1).getDay();

		// 月の日数
		const lastDate = new Date(year, month + 1, 0).getDate();

		// 月初めの空白
		for (let i = 0; i < firstDay; i++) {

			const emptyDay = document.createElement("div");
			emptyDay.className = "calendar-day";

			calendarGrid.appendChild(emptyDay);
		}

		// 日付を表示
		for (let day = 1; day <= lastDate; day++) {

			const dayElement = document.createElement("div");

			dayElement.className = "calendar-day";
			dayElement.textContent = day;

			// 今日の日付を強調
			const today = new Date();

			if (
				year === today.getFullYear() &&
				month === today.getMonth() &&
				day === today.getDate()
			) {
				dayElement.classList.add("today");
			}

			calendarGrid.appendChild(dayElement);
		}
	}

	// 前の月
	if (prevButton) {
		prevButton.addEventListener("click", () => {

			currentDate.setDate(1);
			currentDate.setMonth(currentDate.getMonth() - 1);

			updateMonth();
		});
	}

	// 次の月
	if (nextButton) {
		nextButton.addEventListener("click", () => {

			currentDate.setDate(1);
			currentDate.setMonth(currentDate.getMonth() + 1);

			updateMonth();
		});
	}

	// ジャンルで絞り込み
	if (filterSelect) {

		filterSelect.addEventListener("change", () => {

			const selectedGenre = filterSelect.value;

			document.querySelectorAll("[data-genre]").forEach((item) => {

				const genre = item.dataset.genre;

				item.hidden =
					selectedGenre !== "all" &&
					selectedGenre !== genre;
			});
		});
	}

	// ログアウト確認
	const logoutForm = document.getElementById("logoutForm");

	if (logoutForm) {

		logoutForm.addEventListener("submit", (event) => {

			const confirmed = confirm("ログアウトしますか？");

			if (!confirmed) {
				event.preventDefault();
			}
		});
	}

	// 最初にカレンダーを表示
	updateMonth();

});