document.addEventListener("DOMContentLoaded", () => {
<<<<<<< HEAD
=======
    // 初期表示年月（2026年11月）
    let currentYear = 2026;
    let currentMonth = 11; // 1〜12
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git

<<<<<<< HEAD
	const monthLabel = document.getElementById("monthLabel");
	const calendarGrid = document.getElementById("calendarGrid");
=======
    // HTMLのリストからDB登録済みデータを抽出
    const items = extractItemsFromDOM();
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git

<<<<<<< HEAD
	const prevButton = document.getElementById("prevMonth");
	const nextButton = document.getElementById("nextMonth");
	const filterSelect = document.getElementById("genreFilter");
=======
    // 初期描画
    renderCalendar(currentYear, currentMonth, items);
    calculateSummary(currentYear, currentMonth, items);
    toggleAmountField();

    // 前月ボタン
    document.getElementById("prev-month").addEventListener("click", () => {
        currentMonth--;
        if (currentMonth < 1) {
            currentMonth = 12;
            currentYear--;
        }
        renderCalendar(currentYear, currentMonth, items);
        calculateSummary(currentYear, currentMonth, items);
    });
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git

<<<<<<< HEAD
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
=======
    // 次月ボタン
    document.getElementById("next-month").addEventListener("click", () => {
        currentMonth++;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
        renderCalendar(currentYear, currentMonth, items);
        calculateSummary(currentYear, currentMonth, items);
    });
});

/**
 * HTML内のリスト要素から予定・収支データを配列として取得
 */
function extractItemsFromDOM() {
    const listElements = document.querySelectorAll("#item-list li");
    const data = [];

    listElements.forEach(li => {
        const spans = li.querySelectorAll("span");
        const strong = li.querySelector("strong");
        const borderColor = li.style.borderLeftColor || "#3b82f6";

        const dateStr = spans[0] ? spans[0].textContent.trim() : "";
        const title = strong ? strong.textContent.trim() : "";
        const type = li.getAttribute("data-type"); // SCHEDULE, INCOME, EXPENSE

        // 金額の数値抽出（例: "¥1000" -> 1000）
        let amount = 0;
        if (spans[2]) {
            const numStr = spans[2].textContent.replace(/[^0-9]/g, "");
            amount = numStr ? parseInt(numStr, 10) : 0;
        }

        data.push({ date: dateStr, title, type, amount, color: borderColor });
    });

    return data;
}

/**
 * カレンダーの描画処理
 */
function renderCalendar(year, month, items) {
    const grid = document.getElementById("calendar-grid");
    const headerTitle = document.getElementById("current-month");
    headerTitle.textContent = `${year}年 ${month}月`;
    grid.innerHTML = "";

    // 曜日ヘッダー
    const daysOfWeek = ["日", "月", "火", "水", "木", "金", "土"];
    daysOfWeek.forEach((day, idx) => {
        const div = document.createElement("div");
        div.className = "calendar-day-header" + (idx === 0 ? " sun" : idx === 6 ? " sat" : "");
        div.textContent = day;
        grid.appendChild(div);
    });

    // 月初の曜日と月末日を取得
    const firstDay = new Date(year, month - 1, 1).getDay();
    const lastDate = new Date(year, month, 0).getDate();

    // 空白セル
    for (let i = 0; i < firstDay; i++) {
        const emptyCell = document.createElement("div");
        emptyCell.className = "calendar-cell empty";
        grid.appendChild(emptyCell);
    }

    // 日付セル
    for (let date = 1; date <= lastDate; date++) {
        const cell = document.createElement("div");
        cell.className = "calendar-cell";

        const numSpan = document.createElement("span");
        numSpan.className = "day-num";
        numSpan.textContent = date;
        cell.appendChild(numSpan);

        // YYYY-MM-DD 形式に整形
        const dateStr = `${year}-${String(month).padStart(2, "0")}-${String(date).padStart(2, "0")}`;

        // その日のデータをバッジとして表示
        const dayItems = items.filter(item => item.date === dateStr);
        dayItems.forEach(item => {
            const badge = document.createElement("span");
            badge.className = "calendar-badge";
            badge.setAttribute("data-type", item.type);
            badge.style.backgroundColor = item.color;
            badge.textContent = item.type === "SCHEDULE" ? item.title : `${item.title} ¥${item.amount.toLocaleString()}`;
            cell.appendChild(badge);
        });

        grid.appendChild(cell);
    }
}

/**
 * 選択中の年月の収入・支出・差額を計算してカードに反映
 */
function calculateSummary(year, month, items) {
    const prefix = `${year}-${String(month).padStart(2, "0")}`;
    let income = 0;
    let expense = 0;

    items.forEach(item => {
        if (item.date.startsWith(prefix)) {
            if (item.type === "INCOME") income += item.amount;
            if (item.type === "EXPENSE") expense += item.amount;
        }
    });

    const balance = income - expense;
    document.getElementById("total-income").textContent = `¥${income.toLocaleString()}`;
    document.getElementById("total-expense").textContent = `¥${expense.toLocaleString()}`;
    document.getElementById("total-balance").textContent = `¥${balance.toLocaleString()}`;
}

/**
 * 表示モード切替（「すべて」「予定」「収支」）
 */
function filterItems(mode) {
    // タブのアクティブ表示切替
    document.querySelectorAll(".tab-btn").forEach(btn => btn.classList.remove("active"));
    event.target.classList.add("active");

    // リスト項目とカレンダーバッジの両方をフィルタリング
    const targets = document.querySelectorAll("#item-list li, .calendar-badge");
    targets.forEach(el => {
        const type = el.getAttribute("data-type");
        if (mode === "ALL") {
            el.style.display = "";
        } else if (mode === "SCHEDULE") {
            el.style.display = (type === "SCHEDULE") ? "" : "none";
        } else if (mode === "FINANCE") {
            el.style.display = (type === "INCOME" || type === "EXPENSE") ? "" : "none";
        }
    });
}

/**
 * 種別が「予定」のときは金額入力欄を隠す
 */
function toggleAmountField() {
    const typeSelect = document.getElementById("type-select");
    const amountGroup = document.getElementById("amount-group");
    if (!typeSelect || !amountGroup) return;

    if (typeSelect.value === "SCHEDULE") {
        amountGroup.style.display = "none";
    } else {
        amountGroup.style.display = "block";
    }
}
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git
